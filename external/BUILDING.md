
The submodules in the "external" folder were created with: 
> cd [...]/skia4j/external
> git submodule add https://github.com/google/skia.git
> git submodule add https://chromium.googlesource.com/chromium/tools/depot_tools.git

On older versions of git, you may need to also do the following to clone the submodule contents
> git submodule update --init --recursive



Useful reading: https://github.com/mono/SkiaSharp/wiki/Building-on-Linux

Prerequisites:
- git
- Python 2.7
- Microsoft Visual Studio 2017+       (Community edition is okay)
- Build Tools for Visual Studio 2017  (https://www.visualstudio.com/downloads/#build-tools-for-visual-studio-2017)
- LLVM 8+

Run ** Git Bash **

$ cd [...]/external/skia


Synchronise the skia dependencies
$ python tools/git-sync-deps


$ bin/gn gen out/Static --args='is_official_build=true target_os="win" target_cpu="x64" skia_use_system_expat=false skia_use_system_libjpeg_turbo=false skia_use_system_libpng=false skia_use_system_libwebp=false skia_use_system_zlib=false skia_use_system_icu=false skia_use_system_harfbuzz=false cc="clang" cxx="clang++" clang_win="C:\Program Files\LLVM" win_vc="C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC"'


   (Optional) generate gn debug files as a record of the gn settings
   $ bin/gn gen out/Debug --args='<... same args ...>'

   Now you can get a list of all gn settings using:
   $ bin/gn args out/Debug --list


Compile Skia
$ [..may need absolute path..]/depot_tools/ninja -C out/Static


Copy skia.lib to its final location

$ cp out/Static/skia.lib ../../native/win64/skia-win-x64.lib


Now switch to a windows prompt and build the DLL with:

> python build-dll.py









----------------------OLD----------------------
(Optional) Run gn to generate gn debug data
$ bin/gn gen out/Debug --args='is_official_build=true win_vc="C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC"'
Done. Made 50 targets from 36 files in 576ms

Now you can get a list of all gn settings using:
$ bin/gn args out/Debug --list

 
Run gn to generate Ninja build files
> bin\gn gen out/Shared "--args=is_official_build=true is_component_build=true skia_use_system_expat=false skia_use_system_libjpeg_turbo=false skia_use_system_libpng=false skia_use_system_libwebp=false skia_use_system_zlib=false win_vc="""C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC""""

or to build with clang use: (git bash version)
> bin\gn gen out/Shared --args='is_official_build=true target_os="win" target_cpu="x64" skia_use_system_expat=false skia_use_system_libjpeg_turbo=false skia_use_system_libpng=false skia_use_system_libwebp=false skia_use_system_zlib=false win_vc="C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC" clang_win="C:\Program Files\LLVM"'

$ bin/gn gen out/Shared --args='is_official_build=true target_os="win" target_cpu="x64" skia_use_system_expat=false skia_use_system_libjpeg_turbo=false skia_use_system_libpng=false skia_use_system_libwebp=false skia_use_system_zlib=false cc="clang" cxx="clang++" clang_win="C:\Program Files\LLVM" win_vc="C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC"'

Compile and link Skia
> ..\depot_tools\ninja -C out/Shared
