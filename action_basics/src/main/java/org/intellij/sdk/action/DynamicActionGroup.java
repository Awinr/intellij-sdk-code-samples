// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.action;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.SdkIcons;
import org.jetbrains.annotations.NotNull;

/**
 * 演示在plugin.xml中静态地将操作组添加到菜单，然后在其中创建菜单项
 * 运行时的组。请参阅plugin.xml以获取{@link DynamicActionGroup}的声明，并记下该组
 * 声明不包含操作。 {@link DynamicActionGroup} 基于 {@link ActionGroup}，因为菜单
 * 孩子的决定取决于规则，而不仅仅是位置限制。
 */
public class DynamicActionGroup extends ActionGroup {

/**
   * 返回该组的菜单操作数组。
   *
   * @param e 选择关联的组 ID 菜单时收到的事件。
   * @return AnAction[] {@link AnAction} 的实例，在本例中包含
   * {@link PopupDialogAction} 类。
   */
  @Override
  public AnAction @NotNull [] getChildren(AnActionEvent e) {
    return new AnAction[]{
            new PopupDialogAction("Action Added at Runtime", "Dynamic Action Demo", SdkIcons.Sdk_default_icon)
    };
  }

}
