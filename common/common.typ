#import "@preview/prequery:0.2.0": prequery

#let template(doc) = context {
  set page(height: auto)
  show link: underline
  set quote(block: true)
  doc
}

#let embedClass(name: str, label: none, ..arguments) = {
  show figure: set block(width: 100%)
  show figure: set align(left)
  show figure.caption: set align(center)
  let directory = arguments.named().at("directory", default: "")
  let directory = if directory.len() == 0 { "" } else { directory + "/" }
  let path = arguments.named().at("path", default: "../" + directory + name + ".java")
  let kind = arguments.named().at("kind", default: "Class")
  [
    #figure(caption: name, kind: kind, supplement: [#kind], raw(read(path), lang:"java", block: true))
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