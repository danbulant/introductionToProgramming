When a command runner is desired in the function, query functionality can be used.

In `common/common.typ`, there's a `runCommand` function.

This can be queried using `typst query --input prequery-fallback=true --field value doc.typ '<cmd>'`. For more info see https://typst.app/universe/package/prequery.