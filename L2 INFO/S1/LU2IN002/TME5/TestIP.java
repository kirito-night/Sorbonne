public class TestIP{
	public static void main(String[] args){
		System.out.println(GenerateurIP.getAdresseIP());
		
		for(int i = 0; i < 255*257; i++)
		{
			System.out.println(GenerateurIP.getAdresseIP());
		}
	}
}
