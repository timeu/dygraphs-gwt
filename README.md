## What is Dygraphs-GWT?


Dygraphs-GWT is a wrapper for the [dygraphs][0] chart library. 
The wrapper makes it easy to use dygraphs in any GWT application by providing a
type-safe abstraction of the dygraphs API. 

## How do I use it?

TODO



## How do I install it?

If you're using Maven, you can add the following to your `<dependencies>`
section:

```xml
    <dependency>
      <groupId>com.github.timeu.dygraphs-gwt</groupId>
      <artifactId>dygraphs-gwt</artifactId>
      <version>1.0.0</version>
    </dependency>
```

You can also download the [jar][1] directly or check out the source using git
from <https://github.com/timeu/dygraphs-gwt.git> and build it yourself. Once
you've installed Dygraphs-GWT, be sure to inherit the module in your .gwt.xml
file like this:

```xml
    <inherits name='com.github.timeu.Dygraphs'/>
```

## Where can I learn more?

 * For more details on the Dygraphs-GWT API, consult the [Javadoc][2].
 * Check out the [sample app][3] for a full example of using Dygraphs-GWT.
 
[0]: https://http://dygraphs.com/
[1]: http://search.maven.org/remotecontent?filepath=com/github/timeu/dygraphs-gwt/dygraphs-gwt/1.0.0/dygraphs-gwt-1.0.0.jar
[2]: http://timeu.github.io/dygraphs-gwt/javadoc/
[3]: https://github.com/timeu/dygraphs-gwt/tree/master/dygraphs-gwt-sample/src/main/java/sample/client
