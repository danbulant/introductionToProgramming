{
  pkgs ? import <nixpkgs> { },
}:
pkgs.mkShell rec {
  buildInputs = with pkgs; [
    jdk
  ];
  nativeBuildInputs = with pkgs; [
    jre
  ];
}
