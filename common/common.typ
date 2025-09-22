
#let template(doc) = [
  #set page(height: auto)
  #show link: underline
  #set quote(block: true)
  #doc
]

#let embedClass(name: str) = {
  show figure: set align(left)
  show figure.caption: set align(center)
  figure(caption: name, raw(read("../" + name + ".java"), lang:"java", block: true))
}