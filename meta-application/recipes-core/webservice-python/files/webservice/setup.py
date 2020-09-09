#!/usr/bin/env python3

from setuptools import setup, find_packages

setup(
    name="echo-server",
    version="1.0.0",
    description="Minimal HTTP echo service",
    packages=find_packages(),
    python_requires=">=3.6",
    entry_points={
        "console_scripts": [
            "echo-server=echo_server.main:main",
        ],
    },
    classifiers=[
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ],
)
