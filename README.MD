### kapsule

![Maven Central Version](https://img.shields.io/maven-central/v/io.github.sakethpathike/kapsule?style=for-the-badge&labelColor=131318&color=BFC2FF)

**kapsule** is a Kotlin Multiplatform library
that wraps `kotlinx.html` with Jetpack Compose-style modifiers and layout semantics,
making static HTML generation simpler and more expressive.
Chain Modifier calls for CSS, compose layouts like in Compose,
and avoid unnecessary overhead—just clean, structured HTML.

*I originally built kapsule for my use cases, like the translation editor in the Linkora Localization Server. It’s still a personal project, but I’ve open-sourced it in case others find it helpful. Feel free to use it! Just note that I may not actively maintain it regularly.*

## Documentation

[Documentation (built with kapsule) →](https://kapsule-docs.onrender.com/)  
_What you see is what kapsule can do._
> Docs are a work-in-progress. But if you're familiar with Jetpack Compose, kapsule will feel instantly familiar.
> I’ve included comments in the code for most modifiers, so your IDE's doc preview should help you out.

## Available Components

- Surface
- Text
- Column
- Row
- Spacer
- Box
- Button
- TextInputField
- BreakFlow
- Heading
- Image
- StaggeredGrid
- MaterialIcon
- Span
- Div
- InlineCode

## Contribute to Docs

Help improve examples, explanations,
and tutorials in the kapsule-docs repository (Ktor-powered and built with kapsule itself):
https://github.com/sakethpathike/kapsule-docs

---

## Why name it **kapsule**?

The line that inspired the name:

> My rhymin' is a vitamin held without a capsule  
> — Nas, *N.Y. State of Mind*

**kapsule** works the same way.  
Yeah, it _wraps_ `kotlinx.html`, but not like a clunky pill casing. Think of it as a **sublingual tablet**—it dissolves on contact.

### The Irony (Yeah, I see it)

**"No capsule" vs. "_kapsule_"**:  
Nas says his style or rhymes are _understandable_ and _smooth_ while still keeping it raw and pure.  
kapsule uses a capsule... but it immediately dissolves it.

It’s the dissolving kind.  
Write HTML in pure Kotlin with Jetpack Compose-style. Compile it, and the "capsule" vanishes.  
What’s left? Raw HTML. No heavy templating. No runtime bloat.

---

### License

kapsule is licensed under the Apache-2.0 license.
