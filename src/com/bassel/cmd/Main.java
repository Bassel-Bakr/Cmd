package com.bassel.cmd;


public class Main {

		public static void main(String args[]) {
				System.out.println(56);
				Cmd.SH.ex("echo lol,%d", null, 
						new ILineListener(){

								@Override
								public void onNewLine(String result) {
										System.out.println(result);
										// TODO: Implement this method
								}
						});
		}
}
