Jetpack Compose's declarative approach with Kotlin DSL is one of my best experiences writing Compose/Kotlin code.

Well, that wasn't quite the case when dealing with HTML thrown from a server for simple static sites. `kotlinx.html` solves this, but since I'm more familiar with Compose, I thought there should be a library that just does this. There are solutions like Kobweb and Kweb, but they're quite heavy/full-stack-based, rather than *just* HTML generation.

ANDDDD... I have a plan.

![](https://github.com/user-attachments/assets/1dadec04-f358-48b7-a241-56f196b82929)

Guess what, mine works unlike Vander Linde’s.

---

# Kapsule

Kapsule abstracts some low-level `kotlinx.html` components into compose-style building blocks while using `Modifier` chaining to generate CSS styles, similar to Jetpack Compose.

**One thing to note**: Kapsule only uses Compose-*style* not Compose-*behavior*. The behaviour still depends on HTML/CSS (and JS if you add raw scripts). You should look up MDN docs or Stack Overflow when something doesn't behave like mobile Compose - that's the web dev experience.

##### When to use Kapsule?
- Server-side rendering static HTML
- Generating raw HTML+styling without writing manual markup
- Building static/simple pages with Compose-style Kotlin DSL

**Not a fullstack framework**: Kapsule's motto isn't to be "yet another fullstack solution" or overengineer beyond its core goal — it strictly focuses on HTML/CSS generation with a Compose-like feel. If you need more than this, you're probably looking for Kobweb/Kweb instead.

For documentation (generated with Kapsule itself 🦅), see the Kapsule docs.

**Current state**: Still early (not all components/modifiers exist yet), but gives access to `kotlinx.html` under the hood.