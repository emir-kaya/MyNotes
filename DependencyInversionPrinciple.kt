
/**
 * Bu prensip yüksek seviyedeki yapıların düşük seviyedeki yapılara bağımlı olmaması gerektiğini tavsiye eder.
 * Ancak her iki yapı da soyutlamalara bağımlı olmalıdır(interface vb.). Bu prensipin ana fikri bileşenleri olabildiğince
 * birbirinden ayırmaktır. Bu prensibe uyduğumuz zaman, daha test edilebilir, daha modüler ve sürdürülebilir bir kod
 * yazmış oluruz.
 */

/**
 * Bu prensip örneği için bir bildirim gönderme işlevi senaryosunu ele alalım. Öncelikle ihlal edilen durumu ve sonrasında
 * bu prensibe göre kodun aslında nasıl yazılması gerektiğini görelim.
 */


class EmailService1 {
    fun sendEmail(to: String, message: String) {
        println("Email sent to $to: $message")
    }
}

class SmsService1 {
    fun sendSms(to: String, message: String) {
        println("SMS sent to $to: $message")
    }
}

class Notification1 {
    private val emailService = EmailService1()
    private val smsService = SmsService1()

    fun sendEmail(to: String, message: String) {
        emailService.sendEmail(to, message)
    }

    fun sendSms(to: String, message: String) {
        smsService.sendSms(to, message)
    }
}

/**
 * Burada görüldüğü üzere Notification class'ı EmailService ve SmsService classlarına bağımlı durumda. Bu durum
 * Dependecy Inversion prensibini ihlal ediyor. Bu tarz bir yapı yerine bağımlılıkların ortadan kalktığı ve daha modüler
 * bir yapıda senaryomuzu oluşturmamız gerekiyor. Bunun için classlarımız bir soyutlamaya bağlı olmalı. Örneğin bir
 * interface'e bağımlı olmalılar. Bu yapıdaki prensip ihlalini aşağıdaki gibi çözebiliriz.
 */

interface NotificationService {
    fun send(to: String, message: String)
}
class SmsService2 : NotificationService {
    override fun send(to: String, message: String) {
        //business logic
        println("SMS sent to $to: $message")
    }
}
class EmailService2 : NotificationService {
    override fun send(to: String, message: String) {
        //business logic
        println("Email sent to $to: $message")
    }
}

class Notification2(private val notificationService: NotificationService) {
    fun send(to: String, message: String) {
        notificationService.send(to, message)
    }
}

/**
 * Bu şekilde yapılarımızı bir soyutlamaya bağımlı hale getirerek alt ve üst modül classlar arasındaki bağımlılıkları
 * ortadan kaldırmış olduk. Daha modüler, test edilebilir ve sürdürülebilir bir yapı elde ettik. Solid'in Dependency Inversion
 * prensibi bu şekilde açıklanabilir ve örneklendirilebilir.
 */