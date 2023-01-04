## What is Dygraphs-GWT?


Dygraphs-GWT is a wrapper for the [dygraphs][0] chart library. 
The wrapper makes it easy to use dygraphs in any GWT application by providing a
type-safe abstraction of the dygraphs API. 


## How do I use it?

Following dygraphjs example:

```Javascript
new Dygraph(div, "ny-vs-sf.txt", {
  legend: 'always',
  title: 'NYC vs. SF',
  showRoller: true,
  rollPeriod: 14,
  customBars: true,
  ylabel: 'Temperature (F)',
});
```
can be done with the wrapper this way:

```JAVA

DygraphsOptions options = new DygraphsOptions();
options.legend='always'
options.title='NYC vs. SF'
options.showRoller = true;
options.rollPeriod = 14;
options.customBars = true;
options.ylabel = 'Temperature (F)'

Dygraphs dygraphs = new Dygraphs("ny-vs-sf.txt",options);
somePanel.add(dygraphs);

```


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

GeneViewer uses [GWT 2.8's][1] new [JSInterop feature][2] and thus it has to be enabled in the GWT compiler args.
For maven:
```xml
<compilerArgs>
    <compilerArg>-generateJsInteropExports</compilerArg>
</compilerArgs>
```
or passing it to the compiler via `-generateJsInteropExports`

You can also download the [jar][3] directly or check out the source using git
from <https://github.com/timeu/dygraphs-gwt.git> and build it yourself. Once
you've installed Dygraphs-GWT, be sure to inherit the module in your .gwt.xml
file like this:

```xml
    <inherits name='com.github.timeu.Dygraphs'/>
```

## Where can I learn more?

 * Check out the [sample app][4] ([Source Code][5]) for a full example of using GeneViewer.
 
[0]: https://dygraphs.com/
[1]: http://www.gwtproject.org/release-notes.html#Release_Notes_2_8_0_BETA1
[2]: https://docs.google.com/document/d/10fmlEYIHcyead_4R1S5wKGs1t2I7Fnp_PaNaa7XTEk0/edit#heading=h.o7amqk9edhb9
[3]: https://github.com/timeu/dygraphs-gwt/releases
[4]: http://timeu.github.io/dygraphs-gwt
[5]: https://github.com/timeu/dygraphs-gwt/tree/master/dygraphs-gwt-sample/src/main/java/sample/client
