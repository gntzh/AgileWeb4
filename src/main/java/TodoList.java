public interface TodoList {
    /**
     * 根据Todo的id获取Todo
     *
     * @param id
     * @return 若返回值为null，表示给定id的Todo不存在
     */
    Todo get(String id);

    /**
     * 向TodoList中添加Todo
     *
     * @param todo
     * @return 返回成功添加的Todo
     */
    Todo add(Todo todo);

    /**
     * 根据Todo的id删除Todo
     *
     * @param id
     * @return 返回成功添加的返回删除的Todo
     */
    Todo delete(String id);

    /**
     * 更新TodoList中相应的Todo(根据id查找,然后更新其他内容)
     *
     * @param todo 要更改的对象
     * @return 若成功更改,则返回此更改后的Todo
     */
    Todo update(Todo todo);

    /**
     * 标记Todo的为完成
     *
     * @param id
     * @return
     */
    Todo finish(String id);
}
