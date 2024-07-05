// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.action;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 演示如何与 IntelliJ 平台交互的操作类.
 * 该类执行的唯一操作是向用户提供弹出对话框作为反馈。
 * 通常此类由 IntelliJ Platform 框架根据声明进行实例化
 * 在plugin.xml 文件中。但是，当在运行时添加时，该类将由操作组实例化
 */
public class PopupDialogAction extends AnAction {

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.BGT;
  }

  /**
   * IntelliJ Platform 框架使用此默认构造函数基于plugin.xml 实例化此类
   * 声明。仅在 {@link PopupDialogAction} 类中需要，因为第二个构造函数被覆盖。
   */
  public PopupDialogAction() {
    super();
  }

  /**
   * 该构造函数用于支持动态添加的菜单操作。
   * 它设置菜单项要显示的文本和描述。
   * 否则，IntelliJ 平台将使用默认的 AnAction 构造函数。
   *
   * @param text        要显示为菜单项的文本。
   * @param description 菜单项的描述。
   * @param icon        与菜单项一起使用的图标。
   */
  public PopupDialogAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
    super(text, description, icon);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    // 使用该事件，创建并显示一个对话框
    Project currentProject = event.getProject();
    StringBuilder message =
        new StringBuilder(event.getPresentation().getText() + " Selected!");
    // 如果在编辑器中选择了某个元素，请添加有关它的信息。
    Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);
    if (selectedElement != null) {
      message.append("\nSelected Element: ").append(selectedElement);
    }
    String title = "我是一个标题";
    Messages.showMessageDialog(
        currentProject,
        message.toString(),
        title,
        Messages.getInformationIcon());
  }

  @Override
  public void update(AnActionEvent e) {
    // 根据项目是否开放设置可用性
    Project project = e.getProject();
    e.getPresentation().setEnabledAndVisible(project != null);
  }

}
