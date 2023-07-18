import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

class parent{
	public int compute(int num){
		if(num <=1) return num;
		return compute(num-1) + compute(num-2);
	}
}

class Child extends parent {
	public int compute(int num){
		if(num<=1) return num;
		return compute(num-1) + compute(num-3);
	}
}

class good{
	@Test
	public static void main (String[] args){
		parent obj = new Child();
		System.out.print(obj.compute(4));
	}

	@Test
	public void LIST_TEST() {

		List<Long> list = Arrays.asList(1L,2L,3L,4L);
		System.out.print(list.contains(0L));



	}

}

