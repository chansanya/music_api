package com.music.kt

/**
 * @name: DataType
 * @author: leihuangyan
 * @classPath: com.music.kt.DataType
 * @date: 2023/4/28
 * @description:
 */

fun main(args: Array<String>) {
    val a = 6
    val boxedA: Int = a
    val anotherBoxedA: Int = a

    //比较
    eq(boxedA, anotherBoxedA)
    //运算
    yunsuan(a)
    //数组
    array()

    str()

    tioajian()

}

private fun tioajian() {
    var g = if (1 == 1) 1 else "s";

    println("得到if结果$g")

    val x = 3;
    if (x in 1..10) {
        println("x在区间内")
    }


    val sw = 2;

    when(sw){
        1-> {
            println("这是1")
        }
        in 2..3-> {
            println("这是 [2,3]")
        }
        is Int-> {
            println("这是Int")
        }
        !in 10..15-> {
            println("不在[10,15]")
        }
        else-> {
            println("默认")
        }
    }





}



private fun str() {
    //字符
    val str = "我是敲击大猛男";

    str.forEach { i -> print(i) }


    val multiLine = """
        我不知道你再说些什么
        没一句是人话
        一天天大惊小怪的
    """.trimIndent()

    println(multiLine)

    val i = 10
    val s = "i = $i" // 求值结果为 "i = 10"
    println(s)
}

private fun yunsuan(a: Int) {
    println(a.toByte())
    println("左位移" + a.shl(1))
    println("右位移" + a.shr(2))
    println("无符号右移位" + a.ushr(2))
    println("与" + a.and(2))
    println("或" + a.or(2))
    println("异或" + a.xor(2))
    println("反向" + a.inv())
    println("反向" + a.inc())
}

private fun eq(boxedA: Int, anotherBoxedA: Int) {
    //  false，值相等，对象地址不一样
    println(boxedA === anotherBoxedA)
    // true，值相等
    println(boxedA == anotherBoxedA)
}

private fun array() {
    val arrayOf = arrayOf(1, 2, 3)

    val arr = Array(3) { i ->
        i * 5
    };

    arrayOf.forEach { i -> println(i.toString()) }
    arr.forEach { i -> println(i.toString()) }
}