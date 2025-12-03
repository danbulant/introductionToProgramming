{
  pkgs ? import <nixpkgs> { },
}:
pkgs.mkShell rec {
  buildInputs = with pkgs; [
    (jdk.override { enableJavaFX = true; })
    typst
    tinymist
    nushell
  ];
  nativeBuildInputs = with pkgs; [];

  TYPST_FEATURES = "html";
}
