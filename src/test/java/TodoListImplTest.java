import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListImplTest {

    @ParameterizedTest
    @MethodSource("singleData")
    void add(String name, String detail, Boolean finished) {
        TodoList todoList = new TodoListImpl();
        String id = todoList.add(new Todo(name, detail, finished)).getId();
        Todo todo = todoList.get(id);

        assertEquals(name, todo.getName());
        assertEquals(detail, todo.getDetail());
        assertEquals(finished, todo.getFinished());
    }

    @ParameterizedTest
    @MethodSource("data")
    void delete(List<List<String>> data) {
        TodoList todoList = new TodoListImpl();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String id = todoList.add(new Todo(data.get(i).get(0), data.get(i).get(1))).getId();
            assertNotEquals(null, todoList.get(id));
            todoList.delete(id);
            assertEquals(null, todoList.get(id));
            id = todoList.add(new Todo(data.get(i).get(0), data.get(i).get(1))).getId();
            ids.add(id);
        }
        for (int i = 0; i < data.size(); i++) {
            assertNotEquals(null, todoList.get(ids.get(i)));
            todoList.delete(ids.get(i));
            assertEquals(null, todoList.get(ids.get(i)));
        }
    }

    @ParameterizedTest
    @MethodSource("singleData")
    void finish(String name, String detail, Boolean finished) {
        TodoList todoList = new TodoListImpl();
        String id = todoList.add(new Todo(name, detail, finished)).getId();
        Todo todo = todoList.get(id);
        assertEquals(finished, todo.getFinished());
        todoList.finish(id);
        assertEquals(true, todo.getFinished());
    }

    @ParameterizedTest
    @MethodSource("singleData")
    void update(String name, String detail, Boolean finished){
        TodoList todoList = new TodoListImpl();
        String id = todoList.add(new Todo(name, detail, finished)).getId();

        String tarName = getRandomString(20);
        String tarDetail = getRandomString(50);

        boolean tarF = (Math.random() > 0.5);
        Todo tarTodo = new Todo(tarName, tarDetail, tarF, id);
        Todo res = todoList.update(tarTodo);

        assertEquals(id, res.getId());
        assertEquals(tarName, res.getName());
        assertEquals(tarDetail, res.getDetail());
        assertEquals(tarF, res.getFinished());
    }

    String getRandomString(int length){
        String charSet = "1234567890qwertyuiopasdfghjklzxcvbnm";
        StringBuilder buffer = new StringBuilder(charSet);
        StringBuilder res = new StringBuilder();
        int len = buffer.length();
        for (int i = 0; i < length; i++) {
            res.append(buffer.charAt((int) (Math.random() * len)));
        }
        return res.toString();
    }

    static List<Arguments> singleData() {
        return List.of(
                Arguments.arguments("敏捷Web开发大作业", "一定要完成，占比50%！", true),
                Arguments.arguments("复习高数第一章", "略", true),
                Arguments.arguments("复习大物第一章", "略", false),
                Arguments.arguments("体育波比跳", "截止到周六晚24:00", false),
                Arguments.arguments("微博爬虫对接数据库", "略", false)
        );
    }

    static List<Arguments> data() {
        return List.of(
                Arguments.arguments(List.of(
                        List.of("敏捷Web开发作业打包提交", "注意格式")
                )),
                Arguments.arguments(List.of(
                        List.of("呃", "略"),
                        List.of("开学喽", "7.20"),
                        List.of("又开学喽", "8.15")
                )),
                Arguments.arguments(List.of(
                        List.of("敏捷Web开发大作业", "一定要完成，占比50%！"),
                        List.of("复习高数第一章", "略"),
                        List.of("复习大物第一章", "略"),
                        List.of("复习线代第一章", "略"),
                        List.of("微博爬虫对接数据库", "略")
                )),
                Arguments.arguments(List.of(
                        List.of("图书馆还书", "Python标准库"),
                        List.of("复习高数第一章", "略"),
                        List.of("复习大物第一章", "略"),
                        List.of("复习线代第一章", "略"),
                        List.of("微博爬虫对接数据库", "略")
                ))
        );
    }
}