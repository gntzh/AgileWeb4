public interface TodoList {
    /**
     * 根据Todo的id获取Todo
     *
     * @param id
     * @return 若返回值为null，表示给定id的Todo不存在
     */
    public Todo get(String id);

    /**
     * 向TodoList中添加Todo
     *
     * @param todo
     * @return 返回成功添加的Todo
     */
    public Todo add(Todo todo);

    /**
     * 根据Todo的id删除Todo
     *
     * @param id
     * @return 返回成功添加的返回删除的Todo
     */
    public Todo delete(String id);

    /**
     * 标记Todo的为完成
     *
     * @param id
     * @return
     */
    public Todo finish(String id);
}
