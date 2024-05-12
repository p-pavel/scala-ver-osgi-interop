# Call scala 3 from Scala 2.12

Yes, it absolutely possible to have both versions in OSGi container.

The generated interfaces are binary compatible generally so 2.12 and 3 versions can be compiled 
against an interface build for the corresponding scala version

Then we install single api bundle (no matter which version) and both 2.12 and 3 bundles can use it.

## How to run

TBD (provided upon request)

Output:

```sh
karaf@root()> scala212:call3 
From Scala3: Hello, Scala 2.12.10
```
