#import "@preview/prequery:0.2.0": prequery

#let template(doc) = context {
  set page(height: auto)
  show link: underline
  set quote(block: true)
  doc
}

#let embedClass(name: str, label: none) = {
  show figure: set block(width: 100%)
  show figure: set align(left)
  show figure.caption: set align(center)
  [
    #figure(caption: name, kind: "Class", supplement: [Class], raw(read("../" + name + ".java"), lang:"java", block: true))
    #label
  ]
}

#let runCommand(cmd: str, ..args) = prequery(
  (data: cmd, path: args.pos().at(0)),
  <cmd>,
  () => {
    let output = read(..args)
    raw(block: true, output)
  },
  fallback: [
    ```
    ...
    ```
  ]
)