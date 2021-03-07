package sample.kotlin.xyz.srclab.spring.boot.core

import org.springframework.stereotype.Component
import xyz.srclab.spring.boot.bean.BeanProperties
import xyz.srclab.spring.boot.bean.BeanRegistry
import java.util.*

@Component
open class MyBeanRegistry : BeanRegistry() {

    override fun registerSingletons(): Map<String, Any> {
        val result: MutableMap<String, Any> = HashMap()
        result["bean1"] = "bean1"
        result["bean2"] = "bean2"
        return result
    }

    override fun registerBeans(): Set<BeanProperties> {
        val result: MutableSet<BeanProperties> = HashSet()
        val beanProperties = BeanProperties()
        beanProperties.name = "myBean"
        beanProperties.className = MyBean::class.java.name
        result.add(beanProperties)
        return result
    }
}