# Call scala 3 from Scala 2.12

Yes, it absolutely possible to have both versions in OSGi container.

The generated interfaces are binary compatible generally so 2.12 and 3 versions can be compiled 
against an interface build for the corresponding scala version

Then we install single api bundle (no matter which version) and both 2.12 and 3 bundles can use it.

Sure, you can't refer to the corresponding Scala types of the other version in the inteface, but you can use the interface.

Generally you as well could write the interface in Java. This is not a bad idea anyway if you're 
aiming to provide the API that can be used from any JVM language (think like providing gRPC or REST
API but millons time better anyway)

### Side note: writing Java (and rest of the JVM world) friendly Scala interfaces

Remember: the best part of Java is not the crappy language itself, it's the JVM. Java interfaces
are the decent platform ABI.

- Use java collection interfaces (`Map`, `Set` etc ). Scala collection convertes provide lightweight wrappers.
- Check for Higher Kinded Types. Java has no means to represent them
- Check the generated "Java" code using some decompiler ("Show decompiled with CFR" in Metals)
- When `@interface` annotation will be availble -- use it.
- Represent async computations (your cats-effect IO) as `CompletableFuture` or `CompletionStage`. cats-effect provices cheap conversions. Talking about ZIO... my advice -- don't use it, but I'm sure they have something similar.
- When returning parameterized types (like `CompletableFuture[a]`) use explicitly boxed types for `a`. `CompletableFuture[Int]` will result in `CompletableFuture[Object]` in Java, but `CompletableFuture[java.lang.Integer]` will be `CompletableFuture<Integer>`. This is not a problem for Scala, but Java will be happy (as of Scala 3.4.1)

## How to run

TBD (provided upon request)

Output:

```sh
karaf@root()> scala212:call3 
From Scala3: Hello, Scala 2.12.10
```
