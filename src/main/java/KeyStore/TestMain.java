package KeyStore;

public class TestMain {
 public static void main(String[] args) throws InterruptedException {
	KeyStore<String, String> keyStore = new KeyStore<String, String>();
	keyStore.putWithUpdates("Shiva", "kumar",1000,new Callback<String>() {

		@Override
		public String invoke() {
			// TODO Auto-generated method stub
			return "Kumar";
		}
	});
	keyStore.putWithExpiry("gagan", "pattada",1000);
	System.out.println(keyStore.get("Shiva"));
	System.out.println(keyStore.get("gagan"));
	Thread.sleep(500);
	System.out.println(keyStore.get("Shiva"));
	Thread.sleep(500);
	System.out.println(keyStore.get("Shiva"));
	System.out.println(keyStore.get("gagan"));
	
}
}
