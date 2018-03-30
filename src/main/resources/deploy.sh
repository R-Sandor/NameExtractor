#!/bin/bash
cd build
pwd
rsync -auvz -e "ssh" build/docs/ rsandor@atria.cs.odu.edu:./
rsync -auvz -e "ssh" build/reports/ rsandor@atria.cs.odu.edu:./
