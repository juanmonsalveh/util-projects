package com.springnpostgres.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {


	}


	@Test
	void testSolution1() {
		String input1 = "011100";
		String input2 = "111";
		String input3 = "1111010101111";
		String input4 = "1";
		String input5 = "0";

		System.out.println("start");

		System.out.println(solution1(input1));
		//System.out.println(solution(input2));
		//System.out.println(solution(input3));
		//System.out.println(solution(input4));
		//System.out.println(solution(input5));
	}

	public int solution1(String S) {

		int indexFirst1 = S.indexOf('1');
		if (indexFirst1 == -1) return 0;

		String operableString = S.substring(indexFirst1);
		int size = operableString.length();

		int operations = 0;

		for (int i = (size -1); i > -1; i--  ) {
			if(i == 0 && operableString.charAt(i)=='1'){
				operations = operations + 1;
				break;
			}

			if (operableString.charAt(i)=='1') operations = operations+2;
			else operations = operations+1;
		}
		System.out.println("result for:  "+operableString);
		return operations;
	}

}
