package pcomp.Gui;

public class Tools {
	private static StringBuilder resinterprete=new StringBuilder();
	public static void addText(String x) {
		resinterprete.append(x+"\n");
	}
	public static String getText(boolean i) {
		String res = resinterprete.toString();
		//si demande de reset
		if (i) {
			resinterprete=new StringBuilder();
		}
		return res;
	}
}
