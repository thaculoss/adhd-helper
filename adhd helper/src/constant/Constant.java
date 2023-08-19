package constant;

public class Constant {

    /**
     * 设置窗口相关
     */
    public static class Settings {
        /**
         * 设置窗口标题
         */
        public static final String SETTING_WINDOW_TITLE = "adhd小助手";

        /**
         * 窗体模型选择器提示文本
         */
        public static final String SETTING_MODEL_SELECTOR_TEXT = "请选择模型:";

        /**
         * 窗体时间选择器提示文本
         */
        public static final String SETTING_TIME_SELECTOR_TEXT = "请选择冥想周期（单位：分钟）:";

        /**
         * 是否开启锁屏
         */
        public static final String SETTING_LOCK_WINDOWS = "是否强制锁屏功能:";

        /**
         * 鼠标检测
         */
        public static final String SETTING_MOUSE_CHECK = "是否开启鼠标停留检测:";

        /**
         * 初始化时间的可选数组
         */
        public static final String[] TIME_SELECT_ARRAY = new String[]{"1", "30", "40", "60"};

        /**
         * 锁屏值
         */
        public static final String[] LOCK_SELECT_ARRAY = new String[]{"是", "否"};

        /**
         * 鼠标
         */
        public static final String[] MOUSE_SELECT_ARRAY = new String[]{"是", "否"};
    }

    /**
     * 停止窗口相关
     */
    public static class Stop {
        /**
         * title
         */
        public static final String TITLE = "提示";

        /**
         * 成功正文
         */
        public static final String SUCCESS_TEXT = "停止成功！";

        /**
         * 失败正文
         */
        public static final String FAIL_TEXT = "停止失败！";
    }


    public static class Infor {
        /**
         * title
         */
        public static final String TITLE = "分心提示";

        /**
         * 鼠标停留时间
         */
        public static final int STOP_TIME = 10 * 60 * 1000;
        /**
         * 休息时间
         */
        public static final int REST_TIME = 10 * 60 * 1000;
        /**
         * 再战时间
         */
        public static final int FIGHT_TIME = 25 * 60 * 1000;


        public static final String[] HARM = new String[]{
                "长时间注意力涣散会导致头疼"
        };

    }

    /**
     * 提示窗相关
     */
    public enum AlertDialog {
        /**
         * 第一个运动
         */
        S_1("/icons/01.PNG", "冥想一下", "静心");


        /**
         * 装载图片路径
         */
        String imagePath;

        /**
         * 标题文本
         */
        String title;

        /**
         * 提示语文本
         */
        String text;

        AlertDialog(String imagePath, String title, String text) {
            this.imagePath = imagePath;
            this.title = title;
            this.text = text;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }
    }
}
