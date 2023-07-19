package exception

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.Exception

/**
 * 자바의 에러와 관련해서 예외와 관련된 선조격 클래스가 하나 있다. 그것은 바로 Throwable 이다. 구조를 따라가다보면 하위 IOError같은 Error들도 있고 Exception으로 가지를 타고 가면 우리가 흔히 알고 있는 익셉션들이 잔뜩 나열되어 있다.
 * 사실 Error는 우리가 어떻게 할 수 있는 부분이 아니다. 메모리 부족이나 StackOverFlow같은 에러들은 JVM, 즉 가상머신에서 문제가 생겼을 때 발생하는 예외이다. 어플리케이션의 코드레벨에서 발생하는 에외가 아니기 때문에 예외 발생시 복구 할 수 없는 경우이다.
 * 자바에서는 확인된 예외 (checked exception)과 확인되지 않는 예외 (unchecked exception)로 분류하는데 RuntimeException은 상당히 중요한 위치를 가지고 있다. 이펙티브 자바 에서는 이것을 검사 에외(checked exception) 비검사 예외(unchecked exception)로 표현하고 있다.
 * 바로 이 RuntimeException의 하위 클래스들은 컴파일 시점에서 unchecked 즉, 예외를 체크하지 않는다.
 */

/**
 * 코틀린의 에러처리 방법
 * 코틀린 내에서는 checked exception을 강제하지 않는다.
 * 공식 사이트에는 이런 말이 있다 : 예외처리를 하게 하는 것이 개발자의 생산성을 향상시키고 코드 품질을 높여줄 것이라는 결론에 이른다. 그러나 엔터프라이즈급의 프로젝트 경험에서는 오히려 생산성이 떨어지고 코드 품질 역시 증가하지 않는다.
 * 이펙티브 자바에는 이런 말이 있다: 예외는 오직 예외 상황에서만 써야 하낟. 절대로 일상적인 제어 흐름용으로 쓰여서는 안된다.
 * 자바 코드와 비교해보자
 */

//public class Main {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        String message = "Hello! Friend";
//        consoleMessage(message);
//    }
//
//    public static void consoleMessage(String message) throws InterruptedException {
//        System.out.println("before console");
//        Thread.sleep(10000);
//        System.out.println(message);
//
//    }
//}

// checked error을 강제하지 않기 때문에 어떠한 오류도 발생하지 않는다. 물론 try-catch도 가능하다
fun main() {
    val message = "반갑다";
    console(message)
}

fun console(message: String) {
    println("콘솔 전")
    try {
        Thread.sleep(1000)
    } catch (e: InterruptedException) {
        println("에러 발생")
    } finally {
        println("무조건 실행")
    }

    println("message is $message")

}

// 표현식이나 return 문으로 try-catch 가능
fun strToInt(str: String): Int {
    return try {
        str.toInt()
    } catch (e :Exception) {
        0
    }
}

fun strToint2(str:String) = try {
    str.toInt()
} catch (e : Exception) {
    0
}


/**
 * 이펙티브 자바의 내용중 try-finally 보다는 try-with-resources를 사용하라고 한다.
 * 파일을 읽고 쓰는 InputStream, OutputStream이나 Connection같은 녀석들은 사용한 자원을 회수해야 하는 경우가 많다.
 * 대부분 이런 것들은 제대로 처리를 해주지 않으면 메모리 누수 - Memory leak -을 발생하기 때문에 어떤 자원을 이용하고 나면 finally에서 닫아주는 코드를 작성하곤 한다.
 * 코틀린에서는 use 구문을 제공한다.
 */

fun firstLineOfFile(path: String?, defaultValue: String?): String? {
    return try {
        BufferedReader(FileReader(path)).use { br -> br.readLine() }
    } catch (e: IOException) {
        defaultValue
    }

}

fun firstLineOfFile2(path:String?, defaultValue: String?) : String? = try {
    BufferedReader(FileReader(path)).use { br -> return br.readLine()}

} catch (e: Exception) {
    defaultValue;
}


/** 코틀린의 Nothing
 * 1. private constructor를 가지고 있는 클래스기 때문에 인스턴스를 생성할 수 없다.
 * 2. 모든 클래스의 하위 클래스라는 특징을 가지고 있다.
 * 3. 코틀린에서는 throw를 하나의 표현식으로 보고 있는데 이 녀석의 타입이 Nothing 이다.
 * 4. Nothing이 반환 타입으로 끝나면 그 지점으로부터 더 이상 진행되지 않는다.
 * 5. 타입추론을 사용해서는 안된다.
 */

fun fail(message: String = "실패"): Nothing = throw IllegalArgumentException(message)

fun failWithBody(message: String = "실패패"): Nothing {
    throw IllegalArgumentException(message)
}





























