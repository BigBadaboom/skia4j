# Build a version of the site with compressed javascript and css files

import sys, os, re, subprocess, hashlib, datetime, distutils.spawn, shutil 


SKIA_DIR = ".\\skia"
DEPOT_TOOLS_DIR = ".\\depot_tools"
#VISUAL_C_DIR = "C:\\Program Files (x86)\\Microsoft Visual Studio\\2017\\BuildTools\\VC"
VISUAL_C_DIR = "C:\\Program Files (x86)\\Microsoft Visual Studio\\2019\\Community\\VC"
WIN_SDK_DIR = "C:/Program Files/Microsoft SDKs/Azure/.NET SDK/v2.9"

GN_CMD = ".\\bin\\gn"
NINJA_CMD = "..\\depot_tools\\ninja"

GENERATE_VS_SOLUTION_FILES = True


def buildArch(arch, skiaArch, buildWithNinja, destLibFilename):
  """Configure, build and link the library for the given architecture""" 
   
  outDir = os.path.join("out", arch)
  gnFlags = ('is_official_build=true '
             #'is_component_build=true '
             'skia_use_system_expat=false '
             'skia_use_system_libjpeg_turbo=false '
             'skia_use_system_libpng=false '
             'skia_use_system_libwebp=false '
             'skia_use_system_zlib=false '
             'skia_use_icu=false '
             'skia_use_sfntly=false '
             'skia_use_piex=true '
             'skia_use_dng_sdk=false '
             'skia_enable_tools=false '
             'win_vc="""' + VISUAL_C_DIR + '""" '
             #'win_sdk="""' + WIN_SDK_DIR + '""" '
             'target_os="""win""" '
             'target_cpu="""' + skiaArch + '""" ')
             #'extra_cflags=[ "/MD", "/EHsc" ] ')
             # cflags are from SkiaSharp. Are these really needed? /MD (multithreaded lib) /Hsc (exception handling)

  os.chdir(SKIA_DIR)

  sys.stdout.write('\nConfiguring ' + arch + ' build...\n')
  cmd = GN_CMD + " gen " + outDir + ' --args="' + gnFlags + '"'
  if GENERATE_VS_SOLUTION_FILES:
    cmd += ' --ide=vs'
  sys.stdout.write("\n" + cmd + "\n")
  os.system(cmd)

  if buildWithNinja:
    #sys.stdout.write('\nCleaning ' + arch + ' lib...\n')
    #cmd = NINJA_CMD + " clean " + outDir
    #sys.stdout.write("\n" + cmd + "\n")
    #os.system(cmd)
    #
    sys.stdout.write('\nBuilding ' + arch + ' lib...\n')
    cmd = NINJA_CMD + " -C " + outDir
    sys.stdout.write("\n" + cmd + "\n")
    os.system(cmd)

    # Copy built lib to final location for maven
    sys.stdout.write("\nCopying built library to native library location...\n")
    shutil.copyfile(os.path.join(outDir, "skia.lib"), destLibFilename)

  else:
    sys.stdout.write('\nVisual Studio solution (.sln) files created. Please build final lib with VS.\n')
    
  os.chdir("..")


def main():
  # Check that skia and depot_tools have been cloned
  if not os.path.exists(SKIA_DIR):
    sys.stderr.write("Could not find Skia (expected at: \""+ SKIA_DIR +"\").  Please clone it to this directory and retry the build.\n")
    return

  if not os.path.exists(DEPOT_TOOLS_DIR):
    sys.stderr.write("Could not find Depot Tools (expected at: \""+ DEPOT_TOOLS_DIR +"\").  Please clone it to this directory and retry the build.\n")
    return
  
#  if not os.path.exists(VISUAL_C_DIR):
#    sys.stderr.write("Could not find Visual Studio Build Tools (expected at: \""+ VISUAL_C_DIR +"\").  Please install them and retry the build.\n")
#    sys.stdout.write("If you haven't already, please install Visual Studio.  The 2017 (or later) Community edition should be fine.\n")
#    sys.stdout.write("Build Tools for Visual Studio can be downloaded from: https://www.visualstudio.com/downloads/#build-tools-for-visual-studio-2017\n")
#    return

  # CHeck whether the third party dependencies have been downloaded
  if not os.path.exists( os.path.join(SKIA_DIR, 'third_party', 'externals', 'zlib', 'LICENSE') ):  
    fetchdeps = raw_input("Dependencies do not seem to be downloaded. Fetch them now? [Y/n]")
    if fetchdeps == "Y" or fetchdeps == "y" or fetchdeps == "":
      sys.stdout.write("Fetching dependencies...\n")
      os.chdir(SKIA_DIR)
      os.system("python tools/git-sync-deps")
      sys.stdout.write("\npython tools/git-sync-deps\n")
      os.chdir("..")

  #buildArch("Win32", "x86", false)
  buildArch("x64", "x64", True, os.path.join("..", "..", "native", "win64", "skia-win-x64.lib"))
  



if __name__ == "__main__":
  main()
