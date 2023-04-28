package com.music.kt

/**
 * @name: Hellow
 * @author: leihuangyan
 * @classPath: com.music.Hellow
 * @date: 2023/4/28
 * @description:
 */

fun main() {
    println("hello word")
    val add = add(1, 1)
    println("add结果$add")
    unVoid(2, 3)
    unVoid2(2, 3)
    unVoid2(1, 2, 3, 4)

    //有返回 匿名lambda
    val lam: (Int, Int) -> Int = { x, y ->
        x * y
    }
    println(lam(4, 5))

    //无返回 匿名lambda
    val lam2: (Int, Int) -> Unit = { x, y ->
        run {
            println(x * y)
        }
    }
    lam2(3, 5)

    //可变常量
    var varVal = "a";
    println("可常量:$varVal")
    varVal = "b";
    println("可常量:$varVal")
    val valVal = "a";
    println("不可变可常量:$valVal")
    //valVal = "b" 会报错
    nullTest();
    //类型判断
    type()
    //区间
    range()


}


/**
 * int:类型的参数 a,b
 * 返回值:Int
 */
fun add(a: Int, b: Int): Int {
    return a + b;
}

/**
 * int:类型的参数 a,b
 * 无返回
 */
fun unVoid(a: Int, b: Int): Unit {
    val c = a + b;
    println("这是一个无返回方法$c")
}

fun unVoid2(a: Int, b: Int) {
    val c = a + b;
    println("这是一个无返回方法,省略了Unit$c")
}

/**
 * 可变长参数函数 vararg
 */

fun unVoid2(vararg a: Int) {

    a.forEach { i ->
        run {
            println("可变长度 vararg$i")
        }
    }
}


/**
 * 空值测试
 */
fun nullTest() {

    var a: String?;
    a = "1-1"
    println(sub(a, "+++"))
    a = null;

    println(sub2(a, "+++"))
    println(sub3(a, "+++"))
}

/**
 * 入参不能为空
 */
fun sub(old: String, replaceStr: String): String {

    return old.replace("-", replaceStr);
}

/**
 * ? 允许空值
 * !! 空值时抛出异常
 */
fun sub2(old: String?, replaceStr: String): String {

    return try {
        old!!.replace("-", replaceStr);
    } catch (e: Exception) {
        e.message ?: "参数old -> $old 为空发生异常"
    }
}


/**
 * ? 允许空值
 * ?: 空值时设置默认值
 */
fun sub3(old: String?, replaceStr: String): String {
    return old ?: "默认值-".replace("-", replaceStr);
}

fun type() {
    val str: String = "你好啊";
    val count: Int = 1;
    getStringLength(str)
    getStringLength(count)
    getStringLength(false)
}

fun getStringLength(obj: Any): Unit {
    if (obj is String) {
        println("obj 是字符串类型，is判断自动转换,长度 ${obj.length} ")
    }
    if (obj is Int) {
        println("obj 是数字类型,is判断自动转换，obg+1=${obj + 1} ")
    }

    if (obj !is Int && obj !is String) {
        println("obj 不是Int也不是String")
    }
    return
}

fun range() {
    // 输出“1234”
    for (i in 1..4) print(i)
    println()

    // 什么都不输出
    for (i in 4 .. 1) print(i)
    println()

    //输出 4321
    for (i in 4 downTo  1) print(i)
    println()

    // 等同于 1 <= i && i <= 10
    for (i in 1..10) print(i)
    println()

    // 使用 step 指定步长
    // 输出“13”
    for (i in 1..4 step 2) print(i)
    println()

    // 输出“42”
    for (i in 4 downTo 1 step 2) print(i)
    println()

    // 使用 until 函数排除结束元素
    // i in [1, 10) 排除了 10
    for (i in 1 until 10)  print(i)
    println()
}



