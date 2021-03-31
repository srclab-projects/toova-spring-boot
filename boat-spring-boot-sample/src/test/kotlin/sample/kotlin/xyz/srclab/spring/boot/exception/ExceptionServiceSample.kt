package sample.kotlin.xyz.srclab.spring.boot.exception

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.Assert
import org.testng.annotations.Test
import xyz.srclab.common.exception.ExceptionStatus
import xyz.srclab.spring.boot.exception.EnableExceptionService
import xyz.srclab.spring.boot.exception.ExceptionStatusHandler
import xyz.srclab.spring.boot.exception.ExceptionStatusService
import javax.annotation.Resource

@SpringBootTest(classes = [Starter::class])
@EnableExceptionService
class ExceptionServiceSample : AbstractTestNGSpringContextTests() {

    @Resource
    private lateinit var exceptionStatusService: ExceptionStatusService

    @Test
    fun testExceptionStateService() {
        val runtime = exceptionStatusService.toState<ExceptionStatus>(RuntimeException())
        Assert.assertEquals(runtime.code, "102")
        val throwable = exceptionStatusService.toState<ExceptionStatus>(Exception())
        Assert.assertEquals(throwable.code, "101")
    }
}

@Component
open class RuntimeExceptionStatusHandler :
    ExceptionStatusHandler<RuntimeException, ExceptionStatus> {
    override val supportedExceptionType: Class<RuntimeException> = RuntimeException::class.java
    override fun handle(e: RuntimeException): ExceptionStatus {
        return ExceptionStatus.of("102")
    }
}

@Component
open class ThrowableStatusHandler : ExceptionStatusHandler<Throwable, ExceptionStatus> {
    override val supportedExceptionType: Class<Throwable> = Throwable::class.java
    override fun handle(e: Throwable): ExceptionStatus {
        return ExceptionStatus.of("101")
    }
}

@SpringBootApplication
open class Starter