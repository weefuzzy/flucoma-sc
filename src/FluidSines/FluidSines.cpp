/*
Part of the Fluid Corpus Manipulation Project (http://www.flucoma.org/)
Copyright 2017-2019 University of Huddersfield.
Licensed under the BSD-3 License.
See license.md file in the project root for full license information.
This project has received funding from the European Research Council (ERC)
under the European Union’s Horizon 2020 research and innovation programme
(grant agreement No 725899).
*/

#include <clients/rt/SinesClient.hpp>

#include <FluidSCWrapper.hpp>

static InterfaceTable *ft;

PluginLoad(FluidSTFTUGen)
{
  ft = inTable;
  using namespace fluid::client;
  makeSCWrapper<RTSinesClient>("FluidSines", ft);
}
