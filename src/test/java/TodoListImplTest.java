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
                Arguments.arguments("����Web��������ҵ", "һ��Ҫ��ɣ�ռ��50%��", true),
                Arguments.arguments("��ϰ������һ��", "��", true),
                Arguments.arguments("��ϰ�����һ��", "��", false),
                Arguments.arguments("����������", "��ֹ��������24:00", false),
                Arguments.arguments("΢������Խ����ݿ�", "��", false)
        );
    }

    static List<Arguments> data() {
        return List.of(
                Arguments.arguments(List.of(
                        List.of("����Web������ҵ����ύ", "ע���ʽ")
                )),
                Arguments.arguments(List.of(
                        List.of("��", "��"),
                        List.of("��ѧ�", "7.20"),
                        List.of("�ֿ�ѧ�", "8.15")
                )),
                Arguments.arguments(List.of(
                        List.of("����Web��������ҵ", "һ��Ҫ��ɣ�ռ��50%��"),
                        List.of("��ϰ������һ��", "��"),
                        List.of("��ϰ�����һ��", "��"),
                        List.of("��ϰ�ߴ���һ��", "��"),
                        List.of("΢������Խ����ݿ�", "��")
                )),
                Arguments.arguments(List.of(
                        List.of("ͼ��ݻ���", "Python��׼��"),
                        List.of("��ϰ������һ��", "��"),
                        List.of("��ϰ�����һ��", "��"),
                        List.of("��ϰ�ߴ���һ��", "��"),
                        List.of("΢������Խ����ݿ�", "��")
                ))
        );
    }
}