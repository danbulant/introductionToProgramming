ls | where type == "dir" | sort-by name -n | get name | where $it != "common" |
each { |folder|
  cd $folder
  if not ("common" | path exists) {
    ln -s ../common ./common
  }
  if ("doc.typ" | path exists) {
    typst compile doc.typ
  }
  if ($env.CI? | is-empty) {
    let zip = ("../" + $folder + ".zip")
    rm -f $zip
    7z a $zip *
  }
}

typst compile main.typ -o pages main.pdf