package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import constant.Constant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import service.ScheduledService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;


public class AlertDialog extends DialogWrapper {
    /**
     * 窗体文本
     */
    private String text;

    private boolean isLock;

    private CustomOKAction okAction;
    private CustomRestartAction exitAction;

    private long currentTime;

    private Project project;


    public AlertDialog(@Nullable Project project, String title, String text, boolean isLock) {
        super(project, false, true);
        setTitle(title);
        this.project = project;
        this.text = text;
        this.isLock = isLock;
        this.init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return createFrame();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        String left = "注意力已经涣散了，休息一下";
        String right = "工作狂热";
        if (isLock) {
            left = "到点下班！！！！";
            right = "bug free！！！！";
        }
        exitAction = new CustomRestartAction(left);
        okAction = new CustomOKAction(right);
        // 设置默认的焦点按钮
        exitAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{okAction, exitAction};
    }

    /**
     * 再战
     *
     * @return {@link JComponent}
     */

    protected class CustomOKAction extends DialogWrapperAction {

        protected CustomOKAction(@NotNull String name) {
            super(name);
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            // 点击ok的时候进行数据校验
            ScheduledService.getInstance().removeTask();
            if (!isLock) {
                ScheduledService.getInstance().addTask(Constant.Infor.FIGHT_TIME, false);
            } else {
                ScheduledService.getInstance().addTask(0, true);
            }
            close(CANCEL_EXIT_CODE);
        }
    }

    /**
     * 休息
     *
     * @return {@link JComponent}
     */
    protected class CustomRestartAction extends DialogWrapperAction {

        protected CustomRestartAction(@NotNull String name) {
            super(name);
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            // 点击ok的时候进行数据校验
            ScheduledService.getInstance().removeTask();
            if (!isLock) {
                ScheduledService.getInstance().addTask(Constant.Infor.REST_TIME, true);
                close(CANCEL_EXIT_CODE);
                new CountDialog(project, "倒计时", currentTime).show();
            } else {
                ScheduledService.getInstance().addTask(0, true);
                close(CANCEL_EXIT_CODE);
            }
        }
    }

    @Override
    public void show() {
        if (Messages.isMacSheetEmulation()) {
            this.setInitialLocationCallback(() -> {
                JRootPane rootPane = SwingUtilities.getRootPane(this.getWindow().getParent());
                if (rootPane == null) {
                    rootPane = SwingUtilities.getRootPane(this.getWindow().getOwner());
                }

                Point p = rootPane.getLocationOnScreen();
                p.x += (rootPane.getWidth() - this.getWindow().getWidth()) / 2;
                return p;
            });
            this.getPeer().getWindow().setOpacity(0.8F);
            this.setAutoAdjustable(false);
            this.setSize(this.getPreferredSize().width, 0);
        }

        super.show();
    }

    /**
     * 创建容器
     *
     * @return {@link JComponent}
     */
    private JComponent createFrame() {
        Random random = new Random();
        int index = random.nextInt(99) + 1;
        JPanel panel = new JPanel(new BorderLayout());
        if (isLock) {
            try {
                JLabel text = new JLabel("欢迎回来");
                text.setFont(new Font("微软雅黑", Font.BOLD, 15));
                //添加进容器
                panel.add(text, BorderLayout.CENTER);
                panel.setSize(600, 300);
                Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return panel;
        }
        if (index % 2 == 0) {
            JLabel text = new JLabel(this.text);
            text.setFont(new Font("微软雅黑", Font.BOLD, 15));
            //添加进容器
            panel.add(text, BorderLayout.CENTER);
            panel.setSize(600, 300);
        } else {
            Constant.AlertDialog[] values = Constant.AlertDialog.values();
            int num = random.nextInt(values.length);
            URL iconPath = this.getClass().getResource(values[num].getImagePath());
            JLabel label = new JLabel(new ImageIcon(iconPath));
            JLabel text = new JLabel(values[num].getText());
            setTitle("运动一下");
            text.setFont(new Font("微软雅黑", Font.BOLD, 15));
            //添加进容器
            panel.add(text, BorderLayout.PAGE_END);
            panel.add(label, BorderLayout.CENTER);
        }
        currentTime = System.currentTimeMillis();
        return panel;
    }
}
