// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.action;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.Editor;
import icons.SdkIcons;
import org.jetbrains.annotations.NotNull;

/**
 * 创建一个动作组来包含菜单动作。参见 plugin.xml 的声明。
 */
public class CustomDefaultActionGroup extends DefaultActionGroup {

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.BGT;
  }

  /**
   * 由于 {@link CustomDefaultActionGroup} 是从 {@link com.intellij.openapi.actionSystem.ActionGroup} 派生的，
   * 在这个上下文中，{@code update()} 方法确定动作组本身是否应该启用或禁用。
   * 需要活动的编辑器才能启用该组的功能。
   *
   * @param event 当选择关联的组 ID 菜单时接收到的事件。
   */
  @Override
  public void update(AnActionEvent event) {
    // 根据用户是否正在编辑来启用/禁用
    Editor editor = event.getData(CommonDataKeys.EDITOR);
    event.getPresentation().setEnabled(editor != null);
    // 利用这个机会为组设置一个图标。
    event.getPresentation().setIcon(SdkIcons.Sdk_default_icon);
  }

}
