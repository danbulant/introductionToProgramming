#set document(
  title: "Introduction to Programming - Exercise solutions",
  author: "Daniel Bulant"
)

= Introduction to Programming

Collection of solutions to programming exercises as part of Introduction to Programming (EN), taught by Magnus Madsen.

#outline()

#outline(
  title: [Classes],
  target: figure.where(kind: "Class")
)

#{
  let count = 14;
  for week in range(1, count + 1).filter(it => it != 8) {
    let a = "./week" + str(week) + "/doc.typ"
    include a
  }
}
