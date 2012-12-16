wicket-box
==========

Enhancements for your [Wicket](http://wicket.apache.org) components:

- dynamic behaviors utilizing Wicket's build-in jQuery
- lightweight implementation tailored for Server-side rendered markup
- allow resizing of table columns
- stretch markup parts to locations not supported by CSS
- optional cookie persistence
- synchronized scroll between markup
- tested in Firefox, Safari, Chrome, Opera

See our live examples on [http://wicket-box.appspot.com](http://wicket-box.appspot.com) (beware - very slow!).

Themes
------

You have to add a theme (build-in or your own) to your component to enable it for dynamic layouts, e.g.

    add(new BasicTheme());

Components
----------

- DataBox is a DataTable extension which bundles all behaviors into a highly dynamic table
  with fixed headers, column resizing and its body stretched to the available size
- TreeBox is the pendant for TableTree 
- WizardBox streches its content so its control buttons are pinned to the bottom of its markup area.

Dependency
----------

    <dependency>
      <groupId>com.github.svenmeier.wicket-box</groupId>
      <artifactId>wicket-box</artifactId>
      <version>0.6-SNAPSHOT</version>
    </dependency>

For snapshot releases you have to use the [OSS Sonatype Snapshot repository](https://oss.sonatype.org/content/repositories/snapshots/com/github/svenmeier/wicket-box/):

    <repository>
      <id>sonatype-snapshots</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>    	
    </repository>

Releases will soon be available on [Maven central](http://repo1.maven.org/maven2/com/github/svenmeier/wicket-box)
and [OSS Sonatype](https://oss.sonatype.org/content/repositories/releases/com/github/svenmeier/wicket-box).

