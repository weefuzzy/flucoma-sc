# Fluid Corpus Manipulation: SuperCollider Objects Library

This repository hosts code for generating the SC objects and documentation resources for the Fluid Corpus Manipulation Project. Much of the actual code that does the exciting stuff lives in this repository's principal dependency, the [Fluid Corpus Manipulation Library](https://github.com/flucoma/flucoma-core).

## Pre-requisites

- [CMake](http://cmake.org) >= 3.11
- A C++ 14 compliant compiler for Mac or Windows (via XCode tools on Mac, and Visual Studio 17 >= 15.9 on Windows)

## Dependencies

- [SC Source Code](https://github.com/supercollider/supercollider): this is the only dependency we don't (optionally) manage for you, so there must be a version available to point to when you run, using the CMake Variable `SC_PATH` (see below). It can live anywhere on your file system.

These will be downloaded and configured automatically, unless you pass CMake a source code location on disk for each (see below):

- [Fluid Corpus Manipulation Library](https://github.com/flucoma/flucoma-core)
- [Eigen](https://gitlab.com/libeigen/eigen) (3.3.5)
- [HISSTools Library](https://github.com/AlexHarker/HISSTools_Library)

## I'm in a Hurry...

...and you already have a development environment set up, understand CMake, and have the SC source available?

Cool:

```
mkdir -p build && cd build
cmake -DSC_PATH=<location of your SC source> ..
make install
```

This will assemble a clean package in `release-packaging/FluidCorpusManipulation`.

An alternative to setting up / running CMake directly on the command line is to install the CMake GUI, or use to use the curses GUI `ccmake`.

Also, with CMake you have a choice of which build system you use.

- The default on macOS and Linux is `Unix Makefiles`. On macOS you can also use Xcode by passing `-GXcode` to CMake when you first run it.
- The default on Windows is the latest version of Visual Studio installed. However, Visual Studio can open CMake files directly as projects, which has some upsides. When used this way, CMake variables have to be set via a JSON file that MSVC will use to configure CMake.

## Using Manual Dependencies

In some cases you may want to use your own copies of the required libraries. Unless specified, the build system will download these automatically. To bypass this behaviour, use the following cache variables:

- `FLUID_PATH`: location of the Fluid Corpus Manipulation Library
- `FLUID_PARAMDUMP_PATH`: location of `flucoma_paramdump` repository (e.g. for debugging documentation generation)
- `EIGEN_PATH` location of the Eigen library
- `HISS_PATH` location of the HISSTools library

For example, use this to us your own copy of the Fluid Corpus Manipulation Library:

```
cmake -DSC_PATH=<location of your SC source> -DFLUID_PATH=<location of Fluid Corpus Manipulation Library> ..
```

To find out which branches / tags / commits of these we use, look in the top level `CMakeLists.txt` of the Fluid Corpus Manipulation Library for the `FetchContent_Declare` statements for each dependency.

## Compiling for different CPUs

The build system generally assumes an x86 cpu with AVX instructions (most modern x86 CPUs). To build on another kind of CPU (e.g. older than 2012) you can use the `FLUID_ARCH` cache variable to pass specific flags to your compiler. For example use `-DFLUID_ARCH=-mcpu=native` to optimize for your particular CPU.

For ARM, we use the following default set of flags (with the Bela in mind):

```
-march=armv7-a -mtune=cortex-a8 -mfloat-abi=hard -mfpu=neon
```

## Credits 
#### FluCoMa core development team (in alphabetical order)
Owen Green, Gerard Roma, Pierre Alexandre Tremblay

#### Other contributors:
Alex Harker, Francesco Cameli

--

> This project has received funding from the European Research Council (ERC) under the European Union's Horizon 2020 research and innovation programme (grant agreement No 725899).
