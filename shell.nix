{
  pkgs ? import <nixpkgs> { },
}:
pkgs.mkShell rec {
  buildInputs = with pkgs; [
    jdk
    typst
  ];
  nativeBuildInputs = with pkgs; [
    jre
  ];

  TYPST_FEATURES = "html";
}
