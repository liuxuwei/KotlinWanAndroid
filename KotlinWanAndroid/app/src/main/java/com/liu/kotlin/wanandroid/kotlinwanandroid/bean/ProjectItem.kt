package com.liu.kotlin.wanandroid.kotlinwanandroid.bean

/**
 * author: liu
 * date: 2019/1/17 17:32
 * description
 */
class ProjectItem {


    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"chinalwb","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"用组件化的思想实现一个玩Android APP。目前首页、项目、公众号分别以组件的形式显示到了app中。项目地址附上了实现步骤，欢迎实现您自己的组件！欢迎提出问题 欢迎讨论 欢迎赐教。","envelopePic":"http://www.wanandroid.com/blogimgs/76eb003b-7db1-4e00-be3d-3076b48afc8c.png","fresh":false,"id":7775,"link":"http://www.wanandroid.com/blog/show/2483","niceDate":"2019-01-13","origin":"","projectLink":"https://github.com/chinalwb/wan_android","publishTime":1547364225000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"组件化的玩Android App","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 8
     * size : 15
     * total : 109
     */

    var curPage: Int = 0
    var offset: Int = 0
    var isOver: Boolean = false
    var pageCount: Int = 0
    var size: Int = 0
    var total: Int = 0
    var datas: List<DatasBean>? = null

    class DatasBean {
        /**
         * apkLink :
         * author : chinalwb
         * chapterId : 294
         * chapterName : 完整项目
         * collect : false
         * courseId : 13
         * desc : 用组件化的思想实现一个玩Android APP。目前首页、项目、公众号分别以组件的形式显示到了app中。项目地址附上了实现步骤，欢迎实现您自己的组件！欢迎提出问题 欢迎讨论 欢迎赐教。
         * envelopePic : http://www.wanandroid.com/blogimgs/76eb003b-7db1-4e00-be3d-3076b48afc8c.png
         * fresh : false
         * id : 7775
         * link : http://www.wanandroid.com/blog/show/2483
         * niceDate : 2019-01-13
         * origin :
         * projectLink : https://github.com/chinalwb/wan_android
         * publishTime : 1547364225000
         * superChapterId : 294
         * superChapterName : 开源项目主Tab
         * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
         * title : 组件化的玩Android App
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */

        var apkLink: String? = null
        var author: String? = null
        var chapterId: Int = 0
        var chapterName: String? = null
        var isCollect: Boolean = false
        var courseId: Int = 0
        var desc: String? = null
        var envelopePic: String? = null
        var isFresh: Boolean = false
        var id: Int = 0
        var link: String? = null
        var niceDate: String? = null
        var origin: String? = null
        var projectLink: String? = null
        var publishTime: Long = 0
        var superChapterId: Int = 0
        var superChapterName: String? = null
        var title: String? = null
        var type: Int = 0
        var userId: Int = 0
        var visible: Int = 0
        var zan: Int = 0
        var tags: List<TagsBean>? = null

        class TagsBean {
            /**
             * name : 项目
             * url : /project/list/1?cid=294
             */

            var name: String? = null
            var url: String? = null
        }
    }
}
