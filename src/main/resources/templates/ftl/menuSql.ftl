
-- 一级菜单SQL
INSERT INTO `t_sys_menu` (`parent_id`, `name`, `path`, `perms`, `type`, `iconCls`, `order_num`,`status`)
    VALUES (0, '一级菜单', '', NULL, 1, null, 1, 1);
-- 二级菜单父菜单ID
set @parentId = @@identity;

-- 二级菜单SQL
INSERT INTO `t_sys_menu` (`parent_id`, `name`, `path`, `perms`, `type`, `iconCls`, `order_num`,`status`)
    SELECT @parentId, '二级菜单', '${data.varName}/list', NULL, 1, null, 6, 1;

-- 按钮父菜单ID
set @parentId1 = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `t_sys_menu` (`parent_id`, `name`, `path`, `perms`, `type`, `iconCls`, `order_num`,`status`)
    SELECT @parentId1, '查看', null, 'mpys:${data.varName}:view', 2, null, 0, 1;
INSERT INTO `t_sys_menu` (`parent_id`, `name`, `path`, `perms`, `type`, `iconCls`, `order_num`,`status`)
    SELECT @parentId1, '新增', null, 'mpys:${data.varName}:insert', 2, null, 0, 1;
INSERT INTO `t_sys_menu` (`parent_id`, `name`, `path`, `perms`, `type`, `iconCls`, `order_num`,`status`)
    SELECT @parentId1, '编辑', null, 'mpys:${data.varName}:update', 2, null, 0, 1;
INSERT INTO `t_sys_menu` (`parent_id`, `name`, `path`, `perms`, `type`, `iconCls`, `order_num`,`status`)
    SELECT @parentId1, '删除', null, 'mpys:${data.varName}:delete', 2, null, 0, 1;

