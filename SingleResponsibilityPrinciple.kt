
/**
Bir sınıfın ya da fonksiyonun farklı işlevleri bir arada yapması, kodun karmaşık hale gelmesine,
test edilmesinin zorlaşmasına ve değişiklik yapıldığında beklenmedik hataların oluşmasına neden olabilir.
Bu prensip bu tür sorunları önlemek için sınıfların ve fonksiyonların sorumluluklarını net bir
şekilde ayırmayı önerir.
 */

/**
 * Örnek olarak bir kullanıcı yönetimi sınıfı senaryosunu ele alalım. Bu sınıfımızın kullanıcı oluşturmak,
 * kullanıcı giriş çıkışı yapmak, kullanıcıya mail göndermek gibi sorumlulukları olsun.
 */

class UserManager {

    fun createUser(name: String) {
        // business logic
        println("$name adlı kullanıcı oluşturuldu.")
    }

    fun loginUser(){
        //business logic
        println("Giriş yapıldı")
    }

    fun logoutUser(){
        //business logic
        println("Çıkış yapıldı")
    }

    fun sendEmail(email: String, message: String) {
        // business logic
        println("$email adresine e-posta gönderildi: $message")
    }
}

/**
 * Bu tarz bir yapı kullandığımız zaman bu prensibi ihlal etmiş oluruz. Sınıfımızın tek bir sorumluluğu olmaması
 * kod karmaşasına neden olur. Test edilebilirlik azalır ve herhangi bir değişiklikte hata alma olasılığımız oldukça
 * artar.
 */

/**
 * peki bu tarz bir işlemi Solid'in "S" prensibine göre nasıl düzenleriz? Senaryoya göre ve yapılacak işlevlere göre
 * classımızı parçalara ayırmalıyız. Örnek olarak giriş çıkış fonksiyonları farklı bir classta, mail göndemre işlemi
 * farklı bir classta ve kullanıcı oluşturma fonksiyonu farklı bir classta olmalı.
 */

class CreateUserManager {
    fun createUser(name: String) {
        // business logic
        println("$name adlı kullanıcı oluşturuldu.")
    }
}

class AuthService {
    fun loginUser(){
        //business logic
        println("Giriş yapıldı")
    }

    fun logoutUser(){
        //business logic
        println("Çıkış yapıldı")
    }
}

class EmailService {
    fun sendEmail(email: String, message: String) {
        // business logic
        println("$email adresine e-posta gönderildi: $message")
    }
}

/**
 * Projelerimizde bu şekilde bir yaklaşım kullandığımızda daha test edilebilir ve değiştirlebilir kod yazmış oluruz.
 * Yukarıdaki örnekte normalde tek bir class olan yapımı üç tane class'a böldüm ve sorumlulukları mantıklı bir şekilde
 * dağıtmış oldum. Solid'in Single Responsibility prensibi bu şekilde açıklanabilir ve örneklendirileiblir.
 */