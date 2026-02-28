package dev.scx.http.routing.test;

import dev.scx.http.exception.UnauthorizedException;
import dev.scx.http.routing.Router;
import dev.scx.http.x.HttpServer;

import java.io.IOException;

// todo 待改造
public class Test {

    public static void main(String[] args) throws IOException {
        test1();
    }

    public static void test1() throws IOException {
        var l = System.nanoTime();
        var server = new HttpServer();

        var router = Router.of();

//        router.route(-100000).handler(new CorsHandler().addOrigin("http://localhost:18899"));

        router.any("/abc", c -> {
            var bytes = c.request().asBytes();
            System.out.println(bytes.length);
//            var bbb=c.request().body().asMultiPart();
//            for (var aByte : bbb) {
//                System.out.println(aByte.name()+" "+wrap(()-> aByte.inputStream().readAllBytes().length));
//            }
            c.request().response().send("12312313");
//            c.next();
        });

        router.any("/*", c -> {
            System.out.println(c.pathMatch().capture("*"));
            c.next();
        });

        router.get("/hello", c -> {
            c.request().response().send("hello");
        });

        router.get("/path-params/:id", c -> {
            c.request().response().send("id : " + c.pathMatch().capture("id"));
        });

        router.get("/401", c -> {
            throw new UnauthorizedException();
        });

        router.post("/405", c -> {
            System.out.println("405");
        });

        router.get("/last", c -> {
            var r = 1 / 0;
        });

        server.onRequest(router);

        server.start(8080);

        System.out.println("HttpServer 启动完成 !!! 耗时 : " + (System.nanoTime() - l) / 1000_000);
    }

}
