package com.liu.kotlin.wanandroid.kotlinwanandroid

import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * author: liu
 * date: 2019/1/14 16:41
 * description
 */
class MyRule: TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement(){
            override fun evaluate() {
                val methodName = description?.methodName
                print("$methodName 测试开始\n")

                base?.evaluate()

                print("$methodName 测试结束\n")
            }
        }
    }
}