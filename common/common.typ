
#let template(doc) = [
  #set page(height: auto)
  #show link: underline
  #set quote(block: true)
  #doc
]

#let embedClass(name: str, label: none) = {
  show figure: set block(width: 100%)
  show figure: set align(left)
  show figure.caption: set align(center)
  [
    #figure(caption: name, kind: "Class", supplement: [Class], raw(read("../" + name + ".java"), lang:"java", block: true))
    #label
  ]
}