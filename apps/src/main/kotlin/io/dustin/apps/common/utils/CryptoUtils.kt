package io.dustin.apps.common.utils

import org.springframework.security.crypto.bcrypt.BCrypt
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoUtils {
    /**
     * 굉장히 일반적인 코드로 흔하게 사용하는 양방향 방식은 AES채택한다.
     *
     * 이메일/핸드폰번호/주민번호같은 개인정보의 경우에는 비지니스 로직상에서 복호화해서 사용해야 하는 경우가 있다.
     *
     * 따라서 디비에는 암호화해서 저장하고 필요한 경우에는 복화화해서 사용할 수 있도록 한다.
     *
     * 비밀번호의 경우에는 단방향으로 암호화만 하고 matchPassword를 통해 동일한 비밀번호인지 체크만 하도록 설정한다.
     *
     * 이런 이유로 비번찾기는 인증이후 새로 비밀번호를 설정하도록 하는 것이다.
     */

    companion object {
        /** 임의의 secret key AES에서는 16비트 */
        private const val ALGORITHM = "AES"
        private const val ALGORITHM_MODE = "AES/CBC/PKCS5Padding"
        private const val KEY = "123457689basquiatisgreatartistok"
        private val IV = KEY.substring(0, 16)

        fun encrypt(text: String): String {
            val cipher = Cipher.getInstance(ALGORITHM_MODE)
            cipher.init(Cipher.ENCRYPT_MODE,
                SecretKeySpec(KEY.toByteArray(), ALGORITHM),
                IvParameterSpec(IV.toByteArray())
            )
            val encrypted = cipher.doFinal(text.toByteArray(charset(StandardCharsets.UTF_8.name())))
            return Base64.getEncoder().encodeToString(encrypted)
        }

        fun decrypt(cipherText: String): String {
            val cipher = Cipher.getInstance(ALGORITHM_MODE)
            cipher.init(
                Cipher.DECRYPT_MODE,
                SecretKeySpec(KEY.toByteArray(), ALGORITHM),
                IvParameterSpec(IV.toByteArray())
            )
            return String(cipher.doFinal(Base64.getDecoder().decode(cipherText)), StandardCharsets.UTF_8)
        }

        fun encryptPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())

        fun matchPassword(password: String, encrypted: String) = BCrypt.checkpw(password, encrypted)

    }
}