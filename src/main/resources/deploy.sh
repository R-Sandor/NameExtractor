#!/bin/bash
pwd
rsync -auvz -e "ssh" build/website/ rsandor@atria.cs.odu.edu:./Blue2/

