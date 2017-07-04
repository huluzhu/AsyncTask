package com.baway.hujiqinag1505b20170630;

import java.util.List;

/**
 * 请求数据的属性类
 * 将网络请求道的数据根据层数定义Javabean
 * Created by 胡计强 on 2017/06/30.
 */
public class Bean {

    /**
     * ret_code : 200
     * ret_msg : ok
     * listViewPager : ["https://img10.360buyimg.com/da/jfs/t4747/277/1368712300/170619/35098d7f/58f038e0N9b3a0ca5.jpg","https://img14.360buyimg.com/da/jfs/t4915/21/1427207714/81116/b005bb06/58f08963Ndb295b3c.jpg","https://img13.360buyimg.com/da/jfs/t4651/104/2867456043/68336/99da4c16/58f41eaeN5b614a63.jpg"]
     * list : [{"date":"111","id":1,"pic":"http://p1.pstatp.com/large/22c90001cf8b5388ce33","title":" \n他\u201c秘书圈\u201d的人数规模，赶上了周永康","type":1},{"date":"333","id":3,"pic":"http://p1.pstatp.com/list/190x124/e580016ab3624f1ed33","title":"家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱","type":1},{"date":"444","id":4,"pic":"http://p1.pstatp.com/origin/26ec0004cc0249b49e7c","title":"世界上\u201c最恐怖\u201d的景点大盘点，中国竟然排在第一！","type":1},{"date":"6666","id":6,"pic":"http://p3.pstatp.com/list/190x124/216d001357929b02f476","title":" \n张爱朋首回应白百何绯闻事件，短短二句话，白百何后悔看错了吧！","type":1},{"date":"8888","id":8,"pic":"http://p1.pstatp.com/list/190x124/191a00048757f6714455","title":"中国又开工这一重大工程：连美国都造不出来 周边国家沉默不语","type":1},{"date":"99","id":9,"pic":"http://p3.pstatp.com/list/190x124/22c700036549c9b5ff07","title":"中国第一黑老大东北王乔四爷 最终也逃不过法网恢恢","type":1},{"date":"111","id":10,"pic":"http://p3.pstatp.com/list/190x124/22c6000628d79850e6d7","title":"号称中国关系最硬的人，奶奶是慈禧，大伯是光绪，哥哥是宣统！","type":1},{"date":"222","id":11,"pic":"http://p3.pstatp.com/list/190x124/22ca00011911b0a8061c","title":" \n\u201c二婚女和剩女，我该娶哪个啊？求救！\u201d","type":1},{"date":"5555","id":13,"pic":"http://p3.pstatp.com/list/190x124/289200010936452cb125|http://p3.pstatp.com/list/190x124/289e001b286572de932a|http://p1.pstatp.com/list/190x124/2a3500024e883527e0f7","title":"女生的那些小秘密 男生一直以来纳闷的事情","type":3},{"date":"4444","id":14,"pic":"http://p1.pstatp.com/large/22c90001cf8b5388ce33|http://p9.pstatp.com/large/28760006cfe4c0031187|http://p1.pstatp.com/list/190x124/26ed0000b7d375e36fcf","title":"此小国跟三大邻国打了一仗，只剩3万男子，被迫一夫多妻，到现在还没跟我们建交","type":3},{"date":"9087","id":15,"pic":"http://p3.pstatp.com/large/26ba000858afdb6f8a49|http://p1.pstatp.com/list/190x124/28950003ae33f6ae6c67|http://p3.pstatp.com/list/190x124/2892000287b71b7ff381","title":"泰国45岁女星逆生长如美少女，男友比她小23岁\n泰国45岁女星逆生长如美少女，男友比她小23岁","type":3}]
     */

    private int ret_code;
    private String ret_msg;
    private List<String> listViewPager;
    private List<ListBean> list;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public List<String> getListViewPager() {
        return listViewPager;
    }

    public void setListViewPager(List<String> listViewPager) {
        this.listViewPager = listViewPager;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * date : 111
         * id : 1
         * pic : http://p1.pstatp.com/large/22c90001cf8b5388ce33
         * title :
         他“秘书圈”的人数规模，赶上了周永康
         * type : 1
         */

        private String date;
        private int id;
        private String pic;
        private String title;
        private int type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
