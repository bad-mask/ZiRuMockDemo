package com.badmask_zly.zirumockdemo.bean

/**
 * Created by badmask_zly on 2017/8/1.
 *
 * 涉及到的知识点是 kotlin 中关于数据类的使用
 */

/**
 * 数据类必须满足以下要求：
 *      主构造函数需要至少有一个参数；
 *      主构造函数的所有参数需要标记为 val 或 var ；
 *      数据类不能是抽象、开放、密封或者内部的；
 */


/**
 * 自如「合租／整租」首页中的数据
 */
data class RentHome(val status: String, val error_code: String, val error_message: String, val data: RentHomeData)

data class RentHomeData(val select_name: RentContentItem, val entrust: RentContentItem, val introduce: RentContentItem, val about_ziroom: RentContentItem, val video: RentContentItem, val ziroom_product: RentContentItem, val story: RentContentItem)

/**
 * 自如「合租／整租」首页中，通用数据模块
 */
data class RentContentItem(val title: String, val subtitle: String, val content: List<ContentItem>)

/**
 * 自如「合租／整租」首页中，通用数据子模块
 */
data class ContentItem(val img: String, val title: String, val description: String, val target: String, val app: String)


/**
 * 自如「民宿／驿站」首页中的数据
 */
data class MinSuHome(val status: String, val error_code: String, val error_message: String, val data: MinSuHomeData)

data class MinSuHomeData(val syt: List<MinSuAndLifeContentItem>, val mdd: List<MinSuAndLifeContentItem>, val zry: List<MinSuAndLifeContentItem>, val jchd: List<MinSuAndLifeContentItem>, val hxzl: List<MinSuAndLifeContentItem>, val fdgs: List<MinSuAndLifeContentItem>, val ppg: List<MinSuAndLifeContentItem>)

/**
 * 自如「民宿／驿站」首页中，通用数据模块
 */
data class MinSuAndLifeContentItem(val title: String, val subtitle: String, val pic: String, val types: String, val rgb: String, val url: String, val activity_time: String)




















