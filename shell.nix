{
  pkgs ? import <nixpkgs> { },
}:
pkgs.mkShell rec {
  buildInputs = with pkgs; [
    jdk
    typst
    tinymist
    nushell
  ];
  nativeBuildInputs = with pkgs; [
    jre
  ];

  TYPST_FEATURES = "html";
}
