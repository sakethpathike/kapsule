### kapsule
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.sakethpathike/kapsule?style=for-the-badge&labelColor=131318&color=BFC2FF)

**kapsule** is a Kotlin Multiplatform library that wraps `kotlinx.html` with Jetpack Compose-style modifiers and layout semantics. Write HTML with familiar Compose syntax and get clean output with inline CSS generated from modifiers.

## When to use kapsule
- Generate HTML with inline CSS and raw JS without writing manual markup
- Server-side rendering with Compose-style Kotlin DSL
- Creating email templates, documentation, or static websites
- You want Compose syntax but need raw HTML output

**Not a full-stack framework** - kapsule focuses purely on HTML/CSS generation with a Compose-like API. JavaScript logic must be written directly as JavaScript code - kapsule only handles UI structure and styling.

## Setup

```kotlin
dependencies {
    implementation("io.github.sakethpathike:kapsule:0.1.2")
}
```

## Usage

```kotlin
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import sakethh.kapsule.*
import sakethh.kapsule.utils.*

fun main() {
    val html = createHTML().html {
        Surface(
            fonts = listOf("https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"),
            modifier = Modifier.backgroundColor(Colors.surfaceDark).margin(15.px)
        ) {
            Column {
                Text(
                    text = "kapsule",
                    fontFamily = "Inter",
                    fontWeight = FontWeight.Predefined.Medium,
                    fontSize = 16.px,
                    color = Colors.onSurfaceDark,
                )
                Spacer(modifier = Modifier.height(15.px))
                
                val kapsuleGitHubURL = "https://github.com/sakethpathike/kapsule"
                Button(
                    modifier = Modifier.cursor(Cursor.Pointer).height(25.px)
                        .backgroundColor(Colors.ButtonContainerColor), 
                    id = "githubBtn", 
                    onClick = {
                        """
                     window.open("$kapsuleGitHubURL", "_blank");
                    """.trimIndent()
                    }) {
                    Text(
                        text = "Github", 
                        fontSize = 12.px, 
                        fontFamily = "Inter", 
                        color = Colors.ButtonContentColor
                    )
                }
            }
        }
    }
    println(html)
}
```

This generates:

```html
<html>
  <head>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&amp;display=swap" rel="stylesheet">
    <style></style>
  </head>
  <body style="background-color: #131318;  margin: 15px; ">
    <div style="display: flex; flex-direction: column; ">
      <div style="font-weight: 500; color: #E4E1E9; font-family: &quot;Inter&quot;; font-size: 16px; text-align: start; display: inline; ">kapsule</div>
      <div style="height: 15px; "></div>
<button type="button" id="githubBtn" style="cursor: pointer;  height: 25px;  background-color: #BFC2FF; " onclick="window.open(&quot;https://github.com/sakethpathike/kapsule&quot;, &quot;_blank&quot;);">
        <div style="color: #272B60; font-family: &quot;Inter&quot;; font-size: 12px; text-align: start; display: inline; ">Github</div>
      </button></div>
  </body>
</html>
```

## JavaScript in kapsule

kapsule **does not** handle JavaScript logic. All JavaScript must be written directly as JavaScript code. For more complex JavaScript needs, use kotlinx.html's `script()` function directly to generate JS with script tags, since kapsule ships with kotlinx.html.

## Available Components

- Surface, Text, Column, Row, Spacer, Box, Button
- TextInputField, BreakFlow, Heading, Image, StaggeredGrid
- MaterialIcon, Span, Div, InlineCode

Chain modifiers for styling, compose layouts like in Compose, and get clean HTML with inline CSS. No runtime overhead - just static HTML generation with raw JavaScript when needed.

## Documentation

[Documentation (built with kapsule) →](https://kapsule-docs.onrender.com/)  
The docs showcase what kapsule can do since they're built entirely with the library itself. If you know Jetpack Compose, you'll recognize the patterns. Components and modifiers include inline documentation, so your IDE will help you out.

*I built kapsule for my projects like Linkora's browser extension UI and localization editor. I'll accept pull requests, but I may not actively maintain it.*

## Contribute to Docs

Help improve examples, explanations, and tutorials:
https://github.com/sakethpathike/kapsule-docs

---

## Why name it **kapsule**?

The line that inspired the name:
> My rhymin' is a vitamin held without a capsule  
> Nas, *N.Y. State of Mind*

**kapsule** works the same way.  
Yeah, it *wraps* `kotlinx.html`, but not like a clunky pill casing. Think of it as a **sublingual tablet** that dissolves on contact.

### The Irony (Yeah, I see it)

**"No capsule" vs. "_kapsule_"**:  
Nas says his style or rhymes are *understandable* and *smooth* while still keeping it raw and pure.  
kapsule uses a capsule... but it immediately dissolves it.

It's the dissolving kind.  
Write HTML in pure Kotlin with Jetpack Compose-style. Compile it, and the "capsule" vanishes.  
What's left? Raw HTML. No heavy templating. No runtime bloat.

---

### License

kapsule is licensed under the Apache-2.0 License.
